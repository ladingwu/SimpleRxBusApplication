package com.example.simplerxbus.observer;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ListCompositeDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;


public abstract class RxResourceObserver<T> implements Observer<T>, Disposable {
    /** The active subscription. */
    private final AtomicReference<Disposable> s = new AtomicReference<Disposable>();

    /** The resource composite, can never be null. */
    private final ListCompositeDisposable resources = new ListCompositeDisposable();

    /**
     * Adds a resource to this ResourceObserver.
     *
     * @param resource the resource to add
     *
     * @throws NullPointerException if resource is null
     */
    public final void add(@NonNull Disposable resource) {
        ObjectHelper.requireNonNull(resource, "resource is null");
        resources.add(resource);
    }

    @Override
    public final void onSubscribe(Disposable s) {
        if (setOnce(this.s, s, getClass())) {
            onStart();
        }
    }

    private boolean setOnce(AtomicReference<Disposable> upstream, Disposable next, Class<?> observer) {
        ObjectHelper.requireNonNull(next, "next is null");
        if (!upstream.compareAndSet(null, next)) {
            next.dispose();
            if (upstream.get() != DisposableHelper.DISPOSED) {
                reportDoubleSubscription(observer);
            }
            return false;
        }
        return true;
    }

    private void reportDoubleSubscription(Class<?> consumer) {
        RxJavaPlugins.onError(new IllegalStateException(composeMessage(consumer.getName())));
    }

    public String composeMessage(String consumer) {
        return "It is not allowed to subscribe with a(n) " + consumer + " multiple times. "
                + "Please create a fresh instance of " + consumer + " and subscribe that to the target source instead.";
    }
    /**
     * Called once the upstream sets a Subscription on this ResourceObserver.
     *
     * <p>You can perform initialization at this moment. The default
     * implementation does nothing.
     */
    protected void onStart() {
    }

    /**
     * Cancels the main disposable (if any) and disposes the resources associated with
     * this ResourceObserver (if any).
     *
     * <p>This method can be called before the upstream calls onSubscribe at which
     * case the main Disposable will be immediately disposed.
     */
    @CallSuper
    @Override
    public  void dispose() {
        if (DisposableHelper.dispose(s)) {
            resources.dispose();
        }
    }

    /**
     * Returns true if this ResourceObserver has been disposed/cancelled.
     * @return true if this ResourceObserver has been disposed/cancelled
     */
    @Override
    public final boolean isDisposed() {
        return DisposableHelper.isDisposed(s.get());
    }

}
