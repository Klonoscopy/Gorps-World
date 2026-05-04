package world.gorps.gorp.util;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class TickScheduler {
    private static final List<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ArrayList<>();

    public static void queueServerWork(int ticks, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry<>(action, ticks));
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> toRun = new ArrayList<>();

            workQueue.forEach(work -> {
                work.setValue(work.getValue() - 1);
                if (work.getValue() == 0)
                    toRun.add(work);
            });

            toRun.forEach(e -> e.getKey().run());
            workQueue.removeAll(toRun);
        });
    }
}

