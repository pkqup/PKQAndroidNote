package com.android.pkqup.androidnote.rxjava_test;

import android.os.Bundle;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;
import com.android.pkqup.androidnote.rxjava_retrofit_okhttp_test.Address;
import com.android.pkqup.androidnote.rxjava_retrofit_okhttp_test.User;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function4;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiuCun on 2017/12/1.<br>
 * Describe
 */

public class RxJavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

        // 一、创建Observable
        // createObservableOne();
        // createObservableTwo();
        // createObservableThree();
        // createObservableFour();
        // createObservableFive();
        // createObservableSix();
        // createObservableSeven();
        // createObservableEight();

    }

    // 1、create()方式，使用create方法创建Observable（被观察者）
    private void createObservableOne() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                // 当Observable被订阅时，会调用此方法
                e.onNext("1");
                e.onNext("2");
                e.onComplete();// 结束
                e.onNext("3");
            }
        })
                .subscribe(new Observer<String>() {
            // 订阅
            @Override
            public void onSubscribe(Disposable d) {
                KLog.e("-Observer--onSubscribe--");
            }

            @Override
            public void onNext(String s) {
                KLog.e("-Observer--onNext--", s);
            }

            @Override
            public void onError(Throwable e) {
                KLog.e("-Observer--onError--");
            }

            @Override
            public void onComplete() {
                KLog.e("-Observer--onComplete--");
            }
        });
    }

    // 2、just()方式，创建一个Observable并自动调用onNext( )发射数据。
    private void createObservableTwo() {
        Observable.just(1, 2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer integer) {
                KLog.e("-Observer--onNext--", integer);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    // 3、fromIterable()方式，遍历集合，发送每个item。
    private void createObservableThree() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("Hello" + i);
        }
        Observable.fromIterable(list).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }


    // 4、interval( )方式，创建一个按固定时间间隔发射整数序列的Observable，可用作定时器。即按照固定1秒一次调用onNext()方法。
    private void createObservableFour() {
        // 每隔一秒发送一个数字，从0开始计数
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Long aLong) {
                if (aLong == 11) {
                    disposable.dispose();
                }
                KLog.e("", System.currentTimeMillis() + "---" + aLong);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    // 5、range( )方式，创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常。
    // 下面表示发射1到20的数。即调用20次nNext()方法，依次传入1-20数字。
    private void createObservableFive() {
        Observable.range(1, 10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer integer) {
                KLog.e("", System.currentTimeMillis() + "---" + integer);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

        Observable.intervalRange(1, 20, 0, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long aLong) {
                KLog.e("", System.currentTimeMillis() + "---" + aLong);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    // 6、timer( )方式，在一个给定的延迟后发射一个特殊的值。
    private void createObservableSix() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Long aLong) {
                KLog.e("", System.currentTimeMillis() + "---" + aLong);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    // 7、repeat方式，创建一个Observable，该Observable的事件可以重复调用。
    private void createObservableSeven() {
        Observable.just(1, 2).repeat().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Integer integer) {
                KLog.e("", System.currentTimeMillis() + "---" + integer);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    // 8、defer()方式,当观察者订阅时，才创建Observable，并且针对每个观察者创建都是一个新的Observable。以何种方式创建这个Observable对象，当满足回调条件后，就会进行相应的回调。
    private void createObservableEight() {
        Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just("hello");
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String s) {
                KLog.e("", s);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    // 二、RxJava的线程调度
            /*
             * 在RxJava中, 已经内置了很多线程选项供我们选择, 例如有
             *
             * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
             * Schedulers.computation()代表CPU计算密集型的操作,例如需要大量计算的操作
             * Schedulers.newThread() 代表一个常规的新线程
             * AndroidSchedulers.mainThread()代表Android的主线程
             *
             */

    // 三、RxJava操作符
    // 1、Map的作用就是对发送的每一个事件应用一个函数, 使得每一个事件都按照指定的函数去变化
    @OnClick(R.id.bt_map)
    void bt_map() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer * 10 + "";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                KLog.e(s);
            }
        });
    }

    // 2、FlatMap将发送一个发送事件的Observable变换为发送多个事件的Observables，
    // 然后将它们发射的事件合并后放进一个单独的Observable里。flatMap并不保证事件的顺序。
    // 实际使用场景：一个网络请求成功之后，再发起另一个网络请求。
    @OnClick(R.id.bt_flatmap)
    void bt_flatmap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(integer + "", integer * 10 + "", integer * 100 + "")
                        .delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String str) throws Exception {
                KLog.e(str);
            }
        });
    }

    // 3、ConcatMap和FlatMap的作用一样，但是可以保证事件的顺序。
    @OnClick(R.id.bt_concatmap)
    void bt_concatmap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                return Observable.just(integer + "", integer * 10 + "", integer * 100 + "")
                        .delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String str) throws Exception {
                KLog.e(str);
            }
        });
    }

    private long firstTime;
    private long secondTime;
    private long thirdTime;

    // 4、doOnNext()允许我们在每次输出一个元素之前做一些额外的事情。
    @OnClick(R.id.bt_doOnNext)
    void bt_doOnNext() {
        Observable
                .create(new ObservableOnSubscribe<User>() {
                    @Override
                    public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                        User user = new User();
                        user.setAge(1);
                        firstTime = System.currentTimeMillis();
                        Thread.sleep(3000);
                        KLog.e("-user emitter--");
                        emitter.onNext(user);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        user.setAge(2);
                        secondTime = System.currentTimeMillis();
                        KLog.e("-user doOnNext--");
                        KLog.e(secondTime - firstTime);
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<User, ObservableSource<Address>>() {
                    @Override
                    public ObservableSource<Address> apply(final User user) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<Address>() {
                            @Override
                            public void subscribe(ObservableEmitter<Address> e) throws Exception {
                                Address address = new Address();
                                address.setName("address");
                                e.onNext(address);
                                thirdTime = System.currentTimeMillis();
                                KLog.e("-address emitter--");
                                KLog.e(thirdTime - secondTime);
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Address>() {
                    @Override
                    public void accept(Address address) throws Exception {

                    }
                })
                .subscribe(new Consumer<Address>() {
                    @Override
                    public void accept(Address address) throws Exception {
                        KLog.e(address.getName());
                    }
                });
    }

    // 5、filter()操作符根据test()方法中，根据自己想过滤的数据加入相应的逻辑判断，
    // 返回true则表示数据满足条件，返回false则表示数据需要被过滤。
    @OnClick(R.id.bt_filter)
    void bt_filter() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
            }
        }).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                if (integer % 2 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer str) throws Exception {
                KLog.e(str);
            }
        });
    }

    // 6、take()操作符：指定最多可以输出几个结果
    @OnClick(R.id.bt_take)
    void bt_take() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
            }
        }).take(2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer str) throws Exception {
                KLog.e(str);
            }
        });
    }

    // 6、zip()操作符：通过一个函数将多个Observable发送的事件结合到一起，然后发送这些组合到一起的事件。
    //    它按照严格的顺序发送事件。它只发射与发射数据项最少的那个Observable一样多的数据。
    //    应用场景：比如请求多个网络获取数据，全部获取成功后才执行之后的逻辑。
    @OnClick(R.id.bt_zip)
    void bt_zip() {
        Observable<Integer> integerObservable = Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                    Thread.sleep(1000);
//                    e.onNext(1);
//                    Thread.sleep(1000);
//                    e.onNext(2);
//                    e.onNext(3);
                        int a = 1;
                        while (true) {
                            a++;
                            e.onNext(a);
                        }
                    }
                }).subscribeOn(Schedulers.io());

        Observable<Integer> integerObservable2 = Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(10);
                        e.onNext(20);
                        e.onNext(30);
                    }
                }).subscribeOn(Schedulers.io());

        Observable<String> stringObservable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        Thread.sleep(2000);
                        e.onNext("a");
                        Thread.sleep(2000);
                        e.onNext("b");
                    }
                }).subscribeOn(Schedulers.io());
        Observable<String> stringObservable2 = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> e) throws Exception {
                        Thread.sleep(3000);
                        e.onNext("A");
                        Thread.sleep(3000);
                        e.onNext("B");
                    }
                }).subscribeOn(Schedulers.io());

        Observable.zip(integerObservable, stringObservable, integerObservable2, stringObservable2,
                new Function4<Integer, String, Integer, String, String>() {
                    @Override
                    public String apply(Integer integer, String s, Integer integer2, String s2)
                            throws Exception {
                        return integer + integer2 + s + s2;
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        KLog.e(s);
                    }
                });
    }

    // 6、sample操作符是定期扫描源Observable产生的结果，在指定的间隔周期内进行采样
    // 例子1：每2s扫描一次
    @OnClick(R.id.bt_sample)
    void bt_sample() {
        Observable.interval(1, TimeUnit.MILLISECONDS)
                .sample(3, TimeUnit.SECONDS, Schedulers.newThread()).subscribe(
                new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        KLog.e(aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //四、Flowable是为了解决背压（backpressure）问题，而在Observable的基础上优化后的产物。
    //    与Observable不是同一组观察者模式下的成员，Flowable是Publisher与Subscriber这一组观察者模式中Publisher的典型实现，
    //    Observable是ObservableSource/Observer这一组观察者模式中ObservableSource的典型实现；
    //    所以在使用Flowable的时候，可观察对象不再是Observable,而是Flowable;观察者不再是Observer，而是Subscriber。Flowable与Subscriber之间依然通过subscribe()进行关联。
    //    Flowable的缺点：  Flowable发射的数据流，以及对数据加工处理的各操作符都添加了背压支持，附加了额外的逻辑，其运行效率要比Observable低得多。

    //    Flowable的异步缓存池不同于Observable，
    //    Observable的异步缓存池没有大小限制，可以无限制向里添加数据，直至OOM。
    //    而Flowable的异步缓存池有个固定容量，其大小为128。
    //    BackpressureStrategy的作用便是用来设置Flowable通过异步缓存池存储数据的策略。
    //

       /*  public enum BackpressureStrategy {
              ERROR,BUFFER,DROP,LATEST,MISSING
           }  */
    //    BackpressureStrategy.ERROR:异步缓存池有个固定容量，其大小为128。如果放入Flowable的异步缓存池中的数据超限了，则会抛出MissingBackpressureException异常。
    //    BackpressureStrategy.BUFFER:Flowable的异步缓存池同Observable的一样，没有固定大小，可以无限制向里添加数据，不会抛出MissingBackpressureException异常，但会导致OOM。
    //    BackpressureStrategy.DROP:如果Flowable的异步缓存池满了，会丢掉将要放入缓存池中的数据。
    //    BackpressureStrategy.LATEST:，如果缓存池满了，会丢掉将要放入缓存池中的数据，不同的是，不管缓存池的状态如何，LATEST都会将最后一条数据强行放入缓存池中。
    //    BackpressureStrategy.MISSING:通过Create方法创建的Flowable没有指定背压策略，不会对通过OnNext发射的数据做缓存或丢弃处理，需要下游通过背压操作符（onBackpressureBuffer()/onBackpressureDrop()/onBackpressureLatest()）指定背压策略。


    @OnClick(R.id.bt_flowable)
    void bt_flowable() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 128; i++) {
                    e.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        KLog.e(integer);
                    }
                });
    }


}
