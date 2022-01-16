package frc.robot.constants;

public enum Modules{
    Front_Left(0),
    Front_Right(1),
    Rear_Left(2),
    Rear_Right(3);

    private final int value;

    Modules(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}