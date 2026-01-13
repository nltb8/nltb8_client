package lib.astart;

import mchien.code.model.Point;

public interface AStarHeuristic {
   float getEstimatedDistanceToGoal(Point var1, Point var2);
}
