import java.util.ArrayList;

/** Reinforcement presentation; physical form IDs/locations stay intact for undo and logs. */
public final class KomeReserveView {
    static boolean pool(d region){return Game.p!=null&&Game.p.length>176&&(region==Game.p[174]||region==Game.p[176]);}
    public static ArrayList items(d region){
        if(!pool(region))return region.j();
        ArrayList result=new ArrayList();Object[] snapshot=region.j().toArray();
        for(Object item:snapshot)if(item instanceof KomeDie)result.add(item);
        for(Object item:snapshot){
            if(item==null)continue;
            if(item instanceof KomeDie)continue;
            if(item instanceof KomeFigure&&((KomeFigure)item).ruler<5)continue;
            result.add(item);
        }return result;
    }
    public static aq item(d region,int index){if(!pool(region))return region.b(index);ArrayList visible=items(region);return index>=0&&index<visible.size()?(aq)visible.get(index):null;}
    public static int count(Game game,int nativeCount){return pool(game.I)?items(game.I).size():nativeCount;}
}
