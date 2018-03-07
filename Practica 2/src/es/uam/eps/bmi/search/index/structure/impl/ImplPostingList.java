package es.uam.eps.bmi.search.index.structure.impl;

import es.uam.eps.bmi.search.index.structure.Posting;
import es.uam.eps.bmi.search.index.structure.PostingsList;
import es.uam.eps.bmi.search.index.structure.PostingsListIterator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImplPostingList implements PostingsList, Serializable {

    private List<Posting> postings;
    private Iterator<Posting> iterator;

    public ImplPostingList(){
        postings = new ArrayList<>();
    }

    public int size(){ return postings.size(); }

    public void add(Posting posting){ postings.add(posting); }

    public boolean contains(Posting post){ return postings.contains(post); }

    @Override
    public Iterator<Posting> iterator() {

        return new ImplPostingListIterator(postings);
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();

        for(Posting p:postings){
            aux.append(p.getDocID()).append(" ").append(p.getFreq()).append(" ");
        }
        aux.deleteCharAt(aux.length()-1);

        return aux.toString();
    }

    public static PostingsList toList(String postingList) {
        ImplPostingList aux = new ImplPostingList();
        String[] postings = postingList.split(" ");
        for(int i = 0;i<postings.length;i+=2){
            aux.add(new Posting(Integer.parseInt(postings[i]),Long.parseLong(postings[i+1])));
        }
        return aux;
    }

}
