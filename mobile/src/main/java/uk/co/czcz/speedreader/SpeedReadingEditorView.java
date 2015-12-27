package uk.co.czcz.speedreader;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class SpeedReadingEditorView extends RelativeLayout implements SpeedReadingEditorPresenter.View {

    private EditText editorText;
    private Listener listener;

    public SpeedReadingEditorView(Context context)
    {
        this(context, null, 0);
    }

    public SpeedReadingEditorView(Context context, AttributeSet attr)
    {
        this(context, attr, 0);
    }

    public SpeedReadingEditorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        editorText = (EditText) findViewById(R.id.text_to_read);
        findViewById(R.id.read_locally).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.requestLaunchLocally(editorText.getText().toString());
            }
        });

        findViewById(R.id.read_wear).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.requestLaunchWear(editorText.getText().toString());
            }
        });
    }

    @Override
    public void displayNoTextError() {
        editorText.setError("You can only speed read when you have entered text.");
    }

    @Override
    public void addListener(Listener listener) {
        this.listener = listener;
    }
}
