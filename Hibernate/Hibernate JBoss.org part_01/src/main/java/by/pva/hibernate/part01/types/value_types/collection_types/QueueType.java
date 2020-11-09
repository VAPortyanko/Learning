package by.pva.hibernate.part01.types.value_types.collection_types;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.hibernate.HibernateException;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.persister.collection.CollectionPersister;
import org.hibernate.usertype.UserCollectionType;

@SuppressWarnings("all")
public class QueueType implements UserCollectionType {

    @Override
    public PersistentCollection instantiate(
            SharedSessionContractImplementor session,
            CollectionPersister persister) throws HibernateException {
        return new PersistentQueue( session );
    }

    @Override
    public PersistentCollection wrap(
            SharedSessionContractImplementor session,
            Object collection) {
        return new PersistentQueue( session, (List) collection );
    }

    @Override
    public Iterator getElementsIterator(Object collection) {
        return ( (Queue) collection ).iterator();
    }

    @Override
    public boolean contains(Object collection, Object entity) {
        return ( (Queue) collection ).contains( entity );
    }

    @Override
    public Object indexOf(Object collection, Object entity) {
        int i = ( (List) collection ).indexOf( entity );
        return ( i < 0 ) ? null : i;
    }

	@Override
    public Object replaceElements(
            Object original,
            Object target,
            CollectionPersister persister,
            Object owner,
            Map copyCache,
            SharedSessionContractImplementor session)
            throws HibernateException {
        Queue result = (Queue) target;
        result.clear();
        result.addAll( (Queue) original );
        return result;
    }

    @Override
    public Object instantiate(int anticipatedSize) {
        return new LinkedList<>();
    }

}