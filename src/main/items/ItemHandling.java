package items;

public abstract class ItemHandling implements Item {
    private boolean open;

    public boolean isOpen() {
        return this.open;
    }

    public boolean open() {
        if(isOpen()) {
            return false;
        }
        else {
            this.open = true;
            return true;
        }
    }

    protected void setOpen(boolean open) {
        this.open = open;
    }
}
