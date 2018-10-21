package com.example.simplerxbus.bus;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by 拉丁吴 on 2018.
 */

class RxSimpleBus {

    private final Subject<Object> normalBus;
    private final Subject<Object> stickyBus;
    private final static RxSimpleBus INSTANCE = new RxSimpleBus();
    private ConcurrentMap<String ,Object> cache = new ConcurrentHashMap<>();

    private RxSimpleBus() {
        normalBus = PublishSubject.create().toSerialized();
        stickyBus = PublishSubject.create().toSerialized();
    }

    public static RxSimpleBus getBus() {
        return INSTANCE;
    }

    public void sendMessage(RxBusMessage rxMsg) {
        normalBus.onNext(rxMsg);
    }

    public void sendStickyMessage(RxBusMessage rxMsg) {
        if (rxMsg == null || rxMsg.getMsg()==null ||rxMsg.getMsg()==null) {
            return;
        }
        cache.put(rxMsg.getType(),rxMsg);
    }

    private Observable<Object> toObserverable(Subject<Object> sub, final String filter) {
        return sub.map(new Function<Object, RxBusMessage>() {

            @Override
            public RxBusMessage apply(Object o) throws Exception {
                return (RxBusMessage) o;
            }
        }).filter(new Predicate<RxBusMessage>() {
            @Override
            public boolean test(RxBusMessage rxBusMessage) throws Exception {
                return rxBusMessage.getType().equals(filter);
            }
        }).map(new Function<RxBusMessage, Object>() {

            @Override
            public Object apply(RxBusMessage rxBusMessage) throws Exception {
                return rxBusMessage.getMsg();
            }
        });
    }

    public Disposable receiveMessageFrom(final String filter, RxBusReceiver<Object> receiver) {
        return getBus().toObserverable(normalBus, filter).subscribeWith(receiver);
    }

    public Disposable receiveStickyMessage(final String filter, RxBusReceiver<Object> receiver) {
        Disposable disposable = getBus().toObserverable(stickyBus, filter).subscribeWith(receiver);
        Object value  = cache.get(filter);
        if (value != null) {
            stickyBus.onNext(value);
        }
        return disposable;
    }

    private boolean hasObservable() {
        return normalBus.hasObservers();
    }
    protected void clearCurMessage(String filter){
        cache.remove(filter);
    }

}
