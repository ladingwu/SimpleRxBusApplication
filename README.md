# SimpleRxBus
> **一个更加简单易用的RxBus**

- 使用简介
    - 集成
    ```
     //请确保原来项目中已经引入了RxJava2.0+的依赖，如没有，请引入
    implementation 'com.ladingwu.library:SimpleRxBus:0.3'
     // 需要v7的support包,如果原项目中已经存在，则不必引入
    implementation 'com.android.support:appcompat-v7:28.0.0'


    ```
    - 发送普通事件
    ```
    RxBusUtils.post("filter_message",mesage);
    ```

    - 接收事件(自动取消订阅)
    ```
    // in Fragment or FragmentActivity
     RxBusUtils.receive(this,"filter_message", new RxBusReceiver<Object>() {
                @Override
                public void receive(Object message) {
                    // handle this
                }
            });
    ```

    - 发送粘性事件
    ```
    RxBusUtils.postSticky("filter_sticky_message",message);
    ```
    - 接收粘性事件(自动取消订阅)
    ```
    // in Fragment or FragmentActivity
    RxBusUtils.receiveSticky(this,"filter_message", new RxBusReceiver<Object>() {
                @Override
                public void receive(Object message) {
                        // handle this
                }
            });
    ```

    - 特殊情况
    ```
    //如果无法拿到Fragment/FragmentActivity的实例，则接收事件的时候，需要自行处理取消注册的工作
        Disposable disposable = RxBusUtils.receive("filter", new RxBusReceiver<Object>() {
            @Override
            public void receive(Object data) {
                // handle this
            }
        });

        // 在合适的时机取消注册
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    ```

- SimpleRxBus的优点
    - 简单，好用，支持普通事件/粘性事件的收发
    - 无需显式的调用注册和取消注册接口，框架会自动注册，并且根据生命周期会自动取消注册
    - 即插即用，无论是新项目还是老项目，都不需要做额外的处理，在这一点上，对老项目尤为友好