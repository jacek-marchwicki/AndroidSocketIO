/*
 * Copyright (C) 2015 Jacek Marchwicki <jacek.marchwicki@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.appunite.websocket.rx.json.messages;

import com.appunite.websocket.rx.json.JsonWebSocketSender;
import com.appunite.websocket.rx.messages.RxEventStringMessage;
import com.google.gson.JsonParseException;

import javax.annotation.Nonnull;

import rx.Observable;
import rx.functions.Func1;

/**
 * Event indicating that json was returned by server and was parsed
 *
 * If {@link JsonParseException} occur than {@link RxJsonEventWrongMessageFormat} event
 * will be served
 *
 * @see RxEventStringMessage
 */
public class RxJsonEventMessage extends RxJsonEventConn {
    @Nonnull
    private final Object message;

    public RxJsonEventMessage(@Nonnull JsonWebSocketSender sender, @Nonnull Object message) {
        super(sender);
        this.message = message;
    }

    /**
     * Served parse message
     * @param <T> Class type of message
     * @return a message that was returned
     *
     * @throws ClassCastException when wrong try to get wrong type of message
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    public <T> T message() throws ClassCastException {
        return (T) message;
    }

    @Override
    public String toString() {
        return "RxJsonEventMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    /**
     * Transform one observable to observable of given type filtering by a type
     *
     * @param clazz type of message that you would like get
     * @param <T> type of message that you would like get
     * @return Observable that returns given type of message
     */
    @Nonnull
    public static <T> Observable.Transformer<RxJsonEventMessage, T> filterAndMap(@Nonnull final Class<T> clazz) {
        return new Observable.Transformer<RxJsonEventMessage, T>() {
            @Override
            public Observable<T> call(Observable<RxJsonEventMessage> observable) {
                return observable
                        .filter(new Func1<RxJsonEventMessage, Boolean>() {
                            @Override
                            public Boolean call(RxJsonEventMessage o) {
                                return o != null && clazz.isInstance(o.message());
                            }
                        })
                        .map(new Func1<RxJsonEventMessage, T>() {
                            @Override
                            public T call(RxJsonEventMessage o) {
                                return o.message();
                            }
                        });
            }
        };
    }
}
