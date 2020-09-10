/*
 * Copyright (c) 2015 The CyanogenMod Project
 * Copyright (c) 2017 The LineageOS Project
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
 * limitations under the License.
 */

package org.lineageos.settings.homebutton;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;

import android.util.Log;

import org.lineageos.settings.homebutton.Constants;
import org.lineageos.settings.homebutton.UpdatedStateNotifier;

public class HomebuttonSettings implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = "Homebutton";

    private final Context mContext;
    private final UpdatedStateNotifier mUpdatedStateNotifier;

    public HomebuttonSettings(Context context, UpdatedStateNotifier updatedStateNotifier) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        loadPreferences(sharedPrefs);
        sharedPrefs.registerOnSharedPreferenceChangeListener(this);
        mContext = context;
        mUpdatedStateNotifier = updatedStateNotifier;
    }

    private void loadPreferences(SharedPreferences sharedPreferences) {
    }
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            boolean updated = true;

            if (Constants.FP_HOME_KEY.equals(key) || Constants.FP_HAPTIC_KEY.equals(key) || Constants.FP_HOME_KEY_OFF.equals(key) || Constants.FP_HAPTIC_SCREENOFF_KEY.equals(key) || Constants.FP_KEYS.equals(key) || Constants.FP_KEY_DBLTAP.equals(key) || Constants.FP_KEY_HOLD.equals(key) ||  Constants.FP_KEY_LEFT.equals(key) || Constants.FP_KEY_RIGHT.equals(key)
                || Constants.FP_KEYS_OFF.equals(key) || Constants.FP_KEY_DBLTAP_OFF.equals(key) || Constants.FP_KEY_HOLD_OFF.equals(key) ||  Constants.FP_KEY_LEFT_OFF.equals(key) || Constants.FP_KEY_RIGHT_OFF.equals(key)) {
                Constants.writePreference(mContext, key);
                updated = false;
            } else {
                updated = false;
            }

            if (updated) {
                mUpdatedStateNotifier.updateState();
            }
        }
}
