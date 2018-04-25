package pthfndr.src.main;
public interface Size {

    public static final int FINE = 0, DIMINUTIVE = 1, TINY = 2, SMALL = 3,
    						MEDIUM = 4,
    						LARGE = 5, HUGE = 6, GARGANTUAN = 7, COLOSSAL = 8;
    public static final String[] name = {"Fine", "Diminutive", "Tiny", "Small", "Medium", "Large", "Huge", "Gargantuan", "Colossal"};
    
    // size modifier
    public static int modifier(int size){
        int mod = 0;
        switch(size){
        case FINE: mod = 8;
            break;
        case DIMINUTIVE: mod = 4;
            break;
        case TINY: mod = 2;
            break;
        case SMALL: mod = 1;
            break;
        case MEDIUM: mod = 0;
            break;
        case LARGE: mod = -1;
            break;
        case HUGE: mod = -2;
            break;
        case GARGANTUAN: mod = -4;
            break;
        case COLOSSAL: mod = -8;
            break;
        }
        
        return mod;
    }
    
}

