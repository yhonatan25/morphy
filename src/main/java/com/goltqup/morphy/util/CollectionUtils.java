package com.goltqup.morphy.util;

import java.util.Collection;

import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;

public class CollectionUtils {

    private CollectionUtils() {
        throw new UnsupportedOperationException();
    }

    public static boolean areEqualCollections(final Collection<?> collection, final Collection<?> collectionToCompare) {
        return areTheSameCollection(collection, collectionToCompare)
                || areEqualNullableCollections(collection, collectionToCompare);
    }

    private static boolean areTheSameCollection(final Collection<?> collection, final Collection<?> collectionToCompare) {
        return collection == collectionToCompare;
    }

    private static boolean areEqualNullableCollections(final Collection<?> collection, final Collection<?> collectionToCompare) {
        return areNotNullCollections(collection, collectionToCompare)
                && isEqualCollection(collection, collectionToCompare);
    }

    private static boolean areNotNullCollections(final Collection<?> collection, final Collection<?> collectionToCompare) {
        return collection != null && collectionToCompare != null;
    }
}
