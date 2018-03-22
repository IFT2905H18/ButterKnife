package ift2905.butterknife_demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    /*

    IMPORTANT: Pour que Butterknife soit activé, il faut ajouter ces lignes dans le build.gradle du module...

    dependencies {

    ...

    compile 'com.jakewharton:butterknife:8.8.1'
    annotationProcessor "com.jakewharton:butterknife-compiler:8.8.1"

    configurations.all {
        resolutionStrategy.force 'com.android.support:support-annotations:27.0.1'
    }

    ...

    }

     */

    // Ceci bind les views à leurs objets respectifs
    @BindView(R.id.red_button)
    Button red_button;
    @BindView(R.id.description_label)
    TextView description_label;
    @BindView(R.id.disable_all)
    Button disable_btn;


    // On peut aussi binder des couleurs, des strings, etc.
    @BindColor(android.R.color.holo_blue_dark)
    int blue;
    @BindColor(android.R.color.holo_red_dark)
    int red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ne pas oublier d'ajouter cette ligne!!!
        ButterKnife.bind(this);

        // À partir d'ici, on peut utiliser les views qu'on a bindés
    }

    // On peut facilement assigner un listener à n'importe quelle fonction
    @OnClick(R.id.red_button)
    public void click_red(Button button) {
        button.setBackgroundColor(red);
    }

    // On peut assigner un listener à plusieurs views en même temps!
    @OnClick({R.id.color_btn1, R.id.color_btn2, R.id.color_btn3})
    public void click_blue(Button button) {
        button.setBackgroundColor(blue);
    }



    // Ceci bind plusieurs views à une liste
    @BindViews({R.id.color_btn1, R.id.color_btn2, R.id.color_btn3, R.id.red_button})
    List<Button> color_buttons;

    // Par exemple, ici, en cliquant sur disable_all, on disable tous les boutons de la liste "color_buttons"
    @OnClick(R.id.disable_all)
    public void disable_all() {

        ButterKnife.apply(color_buttons, TOGGLE_DISABLE);
    }

    // On peut définir des méthodes qui s'applique à plusieurs views en même temps.
    static final ButterKnife.Action<View> TOGGLE_DISABLE = new ButterKnife.Action<View>() {
        @Override
        public void apply(@NonNull View view, int index) {
            if (view.isEnabled()) {
                view.setEnabled(false);
            } else {
                view.setEnabled(true);
            }
        }
    };

    //Doc butter
    static final ButterKnife.Action<View> DISABLE = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            view.setEnabled(false);
        }
    };
    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };




}
