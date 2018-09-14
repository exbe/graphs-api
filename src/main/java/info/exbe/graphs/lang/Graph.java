package info.exbe.graphs.lang;

import java.util.Objects;

public interface Graph  {

    interface Node<ID> extends Visitable {
        ID getID();

        default boolean equalsByID(Object other) {
            if( ! (other instanceof Node)) {
                return false;
            }
            return Objects.equals(getID(), ((Node) other).getID());
        }
    }

    interface Edge<N extends Node, O extends Node> extends Visitable {
        N getNode();
        O getOtherNode();

        default boolean isOrdered() { return false; }
    }


    interface Iterable<VISITABLE extends Visitable, VISITOR extends Visitor> extends java.lang.Iterable<VISITABLE> {

        void accept(VISITOR visitor);
    }

    interface Visitor {}
    interface Visitable {}
}
