package slidingrootnav.callback;

public interface DragStateListener {

    void onDragStart();

    void onDragEnd(boolean isMenuOpened);
}
