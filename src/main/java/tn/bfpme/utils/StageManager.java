package tn.bfpme.utils;

import javafx.stage.Stage;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StageManager {
    private static final Set<Stage> stages = Collections.synchronizedSet(new HashSet<>());
    private static final Map<String, Stage> stageMap = Collections.synchronizedMap(new HashMap<>());

    public static void addStage(Stage stage) {
        stages.add(stage);
    }

    public static void addStage(String name, Stage stage) {
        stageMap.put(name, stage);
        stages.add(stage);
    }

    public static void removeStage(Stage stage) {
        stages.remove(stage);
        stageMap.values().remove(stage);
    }

    public static Stage getStage(String name) {
        return stageMap.get(name);
    }

    public static void closeAllStages() {
        for (Stage stage : new HashSet<>(stages)) {
            if (stage != null) {
                stage.close();
            }
        }
        stages.clear();
        stageMap.clear();
    }

}
