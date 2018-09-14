package info.exbe.graphs.lang;

import java.util.Collection;

public interface IterableGraph<G extends Graph.Visitable, V extends Graph.Visitor, I extends Graph.Iterable<G,V>> extends Graph, Graph.Visitable {
    Collection<? super I> iterators();
}
