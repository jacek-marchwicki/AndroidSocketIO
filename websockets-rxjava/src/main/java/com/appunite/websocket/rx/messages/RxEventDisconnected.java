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

package com.appunite.websocket.rx.messages;

import com.appunite.websocket.NotConnectedException;
import com.appunite.websocket.WebSocketSender;
import com.appunite.websocket.WrongWebsocketResponse;

import javax.annotation.Nonnull;

/**
 * Event indicate that client was disconnected to the server
 *
 * since then all execution on previously returned {@link WebSocketSender} will cause throwing
 * {@link NotConnectedException}
 */
public class RxEventDisconnected extends RxEvent {

    @Nonnull
    private Exception exception;

    public RxEventDisconnected(@Nonnull Exception exception) {
        super();
        this.exception = exception;
    }

    /**
     * Exception that caused disconnection.
     *
     * * {@link InterruptedException} when disconnection was requested
     * * {@link java.io.IOException} when problem with connection
     * * {@link WrongWebsocketResponse} when server returned illegal response
     * @return exception that caused disconnection
     */
    @Nonnull
    public Exception exception() {
        return exception;
    }

    @Override
    public String toString() {
        return "DisconnectedRxEvent{" + "e=" + exception + '}';
    }
}
