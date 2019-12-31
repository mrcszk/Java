package CSV;

import java.util.Comparator;
import java.util.function.Predicate;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> p = a->true;
    Comparator<AdminUnit> cmp = null;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    AdminUnitQuery selectFrom(AdminUnitList src){
        this.src = src;
        return this;
    }

    AdminUnitQuery where(Predicate<AdminUnit> pred){
        this.p = pred;
        return this;
    }

    AdminUnitQuery and(Predicate<AdminUnit> pred){
        this.p = this.p.and(pred);
        return this;
    }

    AdminUnitQuery or(Predicate<AdminUnit> pred){
        this.p = this.p.or(pred);
        return this;
    }

    AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        this.src.sort(cmp);
        return this;
    }

    AdminUnitQuery limit(int limit){
        this.limit = limit;
        return this;
    }

    AdminUnitQuery offset(int offset){
        this.offset = offset;
        return this;
    }

    AdminUnitList execute(){
        if ( cmp != null ) {
            return this.src.filter(p,offset, limit).sort(cmp);
        } else {
            return this.src.filter(p,offset, limit);
        }
    }
}
