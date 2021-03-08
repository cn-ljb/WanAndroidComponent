/*
 * Copyright 2016 nekocode
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.nekocode.rxlifecycle3.transformer;


import androidx.annotation.NonNull;

import cn.nekocode.rxlifecycle3.LifecycleEvent;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.ArrayCompositeDisposable;

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
public class BindLifecycleObservableTransformer<T> extends AbstractBindLifecycleTransformer
        implements ObservableTransformer<T, T> {

    public BindLifecycleObservableTransformer(
            @NonNull Observable<LifecycleEvent> lifecycleObservable,
            @NonNull LifecycleEvent event) {

        super(lifecycleObservable, event);
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return new BindLifecycleObservable(upstream);
    }


    private class BindLifecycleObservable extends Observable<T> {
        private final ObservableSource<T> mUpstream;


        private BindLifecycleObservable(ObservableSource<T> upstream) {
            this.mUpstream = upstream;
        }

        @Override
        protected void subscribeActual(final Observer<? super T> downstream) {
            final ArrayCompositeDisposable frc = new ArrayCompositeDisposable(2);

            downstream.onSubscribe(frc);

            receiveEventCompletable()
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            frc.setResource(0, d);
                        }

                        @Override
                        public void onComplete() {
                            frc.dispose();
                        }

                        @Override
                        public void onError(Throwable e) {
                            frc.dispose();
                        }
                    });

            mUpstream.subscribe(new Observer<T>() {
                @Override
                public void onSubscribe(Disposable d) {
                    frc.setResource(1, d);
                }

                @Override
                public void onNext(T t) {
                    downstream.onNext(t);
                }

                @Override
                public void onComplete() {
                    frc.dispose();
                    downstream.onComplete();
                }

                @Override
                public void onError(Throwable e) {
                    frc.dispose();
                    downstream.onError(e);
                }
            });
        }
    }
}