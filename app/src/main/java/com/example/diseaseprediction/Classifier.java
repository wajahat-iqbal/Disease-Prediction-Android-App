package com.example.diseaseprediction;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;
import org.tensorflow.lite.Interpreter;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
public class Classifier {

    private static final String TAG = Classifier.class.getSimpleName();

    private static final String MODEL_NAME = "kmodel.tflite";


    private final float[][] mOutput = new float[2][41];
    private final Interpreter.Options options = new Interpreter.Options();
    private final Interpreter mInterpreter;

    public Classifier(Activity activity) throws IOException {
        this.mInterpreter = new Interpreter(loadModelFile(activity), options);

    }

    public float[][] classify(float[][] mInput) {

        mInterpreter.run(mInput, mOutput);

        return mOutput;
    }

    private MappedByteBuffer loadModelFile(Activity activity) throws IOException {
        AssetFileDescriptor fileDescriptor = activity.getAssets().openFd(MODEL_NAME);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
}