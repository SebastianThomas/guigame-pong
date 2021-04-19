package guigame.logic.event;

@FunctionalInterface
public interface EventListener {
    void actionPerformed(Event e);
}
