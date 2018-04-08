package es.uam.eps.bmi.recsys;

import es.uam.eps.bmi.recsys.ranking.Ranking;
import es.uam.eps.bmi.recsys.ranking.RankingElement;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RecommendationImpl implements Recommendation{

    Map<Integer,Ranking> userRanking;

    public RecommendationImpl() {
        userRanking = new HashMap<>();
    }

    @Override
    public Set<Integer> getUsers() {
        return userRanking.keySet();
    }

    @Override
    public Ranking getRecommendation(int user) {
        return userRanking.get(user);
    }

    @Override
    public void add(int user, Ranking ranking) {
        userRanking.putIfAbsent(user,ranking);
    }

    @Override
    public void print(PrintStream out) {
            userRanking.forEach((k,v)->{
                v.forEach((e)->print(e,out));
            });
    }

    @Override
    public void print(PrintStream out, int userCutoff, int itemCutoff) {

        int i=0;
        for(Integer user:userRanking.keySet()){
            if(i<userCutoff){
                int j=0;
                for (Iterator<RankingElement> it = getRecommendation(user).iterator(); it.hasNext() && j<itemCutoff;j++ ) {
                    print(it.next(),out);
                }

            }else{
                break;
            }

        }
    }

    public void print(RankingElement e, PrintStream out){
        out.println("El item  con id: "+e.getID()+" tiene el score: "+e.getScore());
    }

}
