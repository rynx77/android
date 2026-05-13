package com.example.expo;

import android.content.Context;
import android.database.Cursor;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void testInsertionProfessionnel() {

        Context appContext =
                InstrumentationRegistry.getInstrumentation().getTargetContext();

        SQLiteDatabaseHelper db =
                new SQLiteDatabaseHelper(appContext);

        Cursor c1 = db.getAllData();

        int nb1 = c1.getCount();

        db.insertData(
                "tabate",
                "rayan",
                "Médecin",
                "toulouse",
                "31100"
        );

        Cursor c2 = db.getAllData();

        int nb2 = c2.getCount();

        assertEquals(nb1 + 1, nb2);
    }
}