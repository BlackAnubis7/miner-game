package engine;

import java.util.ArrayList;

public class Row {
    private final ArrayList<Field> pos;
    private final ArrayList<Field> neg;

    public Row(int posX, int negX) {
        pos = new ArrayList<>();
        neg = new ArrayList<>();
        for(int x=0; x<=posX; x++) {
            Field df = new Field();
            pos.add(df);
        }
        for(int x=0; x<negX; x++) {
            Field df = new Field();
            neg.add(df);
        }
    }

    public Field getField(int x) {
        if(x < 0) {
            while(-x > neg.size()) {
                Field df = new Field();
                neg.add(df);
            }
            return neg.get(-x-1);
        }
        else {
            while(x > pos.size() - 1) {
                Field df = new Field();
                pos.add(df);
            }
            return pos.get(x);
        }
    }
}
