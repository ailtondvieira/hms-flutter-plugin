/*
    Copyright 2021-2023. Huawei Technologies Co., Ltd. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.huawei.hms.flutter.drive.services.batch;

import android.content.Context;
import android.content.IntentFilter;

import com.huawei.agconnect.LocalBrdMnger;

import io.flutter.plugin.common.EventChannel.EventSink;
import io.flutter.plugin.common.EventChannel.StreamHandler;

public class BatchStreamHandler implements StreamHandler {
    private BatchReceiver batchReceiver;
    private final Context context;

    public BatchStreamHandler(final Context context) {
        this.context = context;
    }

    @Override
    public void onListen(final Object arguments, final EventSink events) {
        batchReceiver = new BatchReceiver(events);
        LocalBrdMnger.getInstance(context).registerReceiver(batchReceiver, new IntentFilter(context.getPackageName() + ".BATCH_ACTION"));
    }

    @Override
    public void onCancel(final Object arguments) {
        LocalBrdMnger.getInstance(context).unregisterReceiver(batchReceiver);
    }
}
