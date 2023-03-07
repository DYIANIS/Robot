package robot;

public class MyRobot {

    public static void main(String[] args) {
        Robot robot = new Robot(0, 0, Direction.DOWN);
        moveRobot(robot, 3, 3);
        
        System.out.println(robot.getX() + " | " + robot.getY() + " | ");

    }

    public static void moveRobot(RobotConnectionManager robotConnectionManager, int toX, int toY) {
        
        RobotConnection robotConnection = null;
        int tries = 0;
        
        while(tries <= 3){
            tries++;
            try {
                robotConnection = robotConnectionManager.getConnection();
                robotConnection.moveRobotTo(toX, toY);
                break;
            }
            catch (RobotConnectionException e) {
                if(tries == 3) {
                    throw new RobotConnectionException("ERROR");
                }
            }catch (Exception e) {
                tries = 5;
                throw e;
            }finally {
                if (robotConnection != null) {
                    try {
                        robotConnection.close();
                    }catch (Exception ignore) {
                    }
                }
            }
        }
    }
    
    public static void moveRobot(Robot robot, int toX, int toY) {
        
        while(robot.getY() - toY > 0) {
            if(!robot.getDirection().equals(Direction.DOWN)) {
                while(!robot.getDirection().equals(Direction.DOWN)) {
                    robot.turnRight();
                }
            }
            robot.stepForward();
        }
        
        while(robot.getY() - toY < 0) {
            while(!robot.getDirection().equals(Direction.UP)) {
                robot.turnRight();
            }
            robot.stepForward();
        }
        
        while(robot.getX() - toX > 0) {
            if(!robot.getDirection().equals(Direction.LEFT)) {
                while(!robot.getDirection().equals(Direction.LEFT)) {
                    robot.turnRight();
                }
            }
            robot.stepForward();
        }
        
        while(robot.getX() - toX < 0) {
            if(!robot.getDirection().equals(Direction.RIGHT)) {
                while(!robot.getDirection().equals(Direction.RIGHT)) {
                    robot.turnRight();
                }
            }
            robot.stepForward();
        }
    }
 
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
 
    public static class Robot {
        int x;
        int y;
        Direction dir;
 
        public Robot (int x, int y, Direction dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
 
        public Direction getDirection() {return dir;}
 
        public int getX() {return x;}
 
        public int getY() {return y;}
 
        public void turnLeft() {
            if      (dir == Direction.UP)    {dir = Direction.LEFT;}
            else if (dir == Direction.DOWN)  {dir = Direction.RIGHT;}
            else if (dir == Direction.LEFT)  {dir = Direction.DOWN;}
            else if (dir == Direction.RIGHT) {dir = Direction.UP;}
        }
 
        public void turnRight() {
            if      (dir == Direction.UP)    {dir = Direction.RIGHT;}
            else if (dir == Direction.DOWN)  {dir = Direction.LEFT;}
            else if (dir == Direction.LEFT)  {dir = Direction.UP;}
            else if (dir == Direction.RIGHT) {dir = Direction.DOWN;}
        }
 
        public void stepForward() {
            if (dir == Direction.UP)    {y++;}
            if (dir == Direction.DOWN)  {y--;}
            if (dir == Direction.LEFT)  {x--;}
            if (dir == Direction.RIGHT) {x++;}
        }
    }
}

