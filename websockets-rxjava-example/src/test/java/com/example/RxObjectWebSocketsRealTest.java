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

package com.example;

import com.appunite.websocket.rx.RxWebSockets;
import com.appunite.websocket.rx.object.GsonObjectSerializer;
import com.appunite.websocket.rx.object.RxObjectWebSockets;
import com.example.model.Message;
import com.example.model.MessageType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.disposables.Disposable;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.reactivex.schedulers.Schedulers;

public class RxObjectWebSocketsRealTest {

    private RxObjectWebSockets socket;

    @Before
    public void setUp() throws Exception {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Message.class, new Message.Deserializer())
                .registerTypeAdapter(MessageType.class, new MessageType.SerializerDeserializer())
                .create();

        final RxWebSockets rxWebSockets = new RxWebSockets(new OkHttpClient(),
                new Request.Builder()
                        .get()
                        .url("ws://10.10.0.2:8080/ws")
                        .addHeader("Sec-WebSocket-Protocol", "chat")
                        .build());
        socket = new RxObjectWebSockets(rxWebSockets, new GsonObjectSerializer(gson, Message.class));
    }

    @Test
    @Ignore
    public void testName() throws Exception {
        final Disposable subscribe = socket.webSocketObservable()
                .subscribeOn(Schedulers.io())
                .subscribe();
        Thread.sleep(5000);
        subscribe.dispose();
        Thread.sleep(5000);
    }

}