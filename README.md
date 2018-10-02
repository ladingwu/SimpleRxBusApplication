# SimpleRxBus
> **一个更加简单易用的RxBus**

- 使用简介
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


- SimpleRxBus的优点
    - 简单，易用，支持普通事件/粘性事件的收发
    - 无需显式的调用注册和取消注册接口，框架自动注册，并且根据生命周期会自动取消注册