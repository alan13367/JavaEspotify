package business.entities;

import javax.swing.*;

public class PlayerSlider implements Runnable{

    JSlider slider;

    public PlayerSlider(JSlider slider) {
        this.slider = slider;
    }

    public void moveSlider(JSlider slider) {

        int nValue = slider.getValue();
        int nMaxValue = slider.getMaximum();
        do {
            nValue++;
            slider.setValue(nValue);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
            slider.setValue(nValue);
            System.out.println("Value is " + nValue);
            System.out.println("Max value is " + nMaxValue);
        }while(nValue<nMaxValue);
    }

    public void initSlider(Song song, JSlider jslider) {
        jslider = new JSlider(0, song.getSongSeconds(), 0);
    }

    @Override
    public void run() {

    }
}
