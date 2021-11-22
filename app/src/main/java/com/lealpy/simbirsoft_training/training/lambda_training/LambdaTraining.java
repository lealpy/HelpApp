package com.lealpy.simbirsoft_training.training.lambda_training;

import android.util.Log;

public class LambdaTraining {

    public void program() {
        Runnable myClosure = () -> Log.i("Information", "I love Java");

        myClosure.print();
        repeatTask(10, myClosure);
    }

    public void repeatTask (int times, Runnable task) {
        for(int i = 0; i < 10; i++) {
            task.print();
        }
    }

}