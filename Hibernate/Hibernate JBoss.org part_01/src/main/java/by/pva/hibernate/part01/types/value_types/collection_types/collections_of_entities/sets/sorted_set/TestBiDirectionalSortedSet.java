package by.pva.hibernate.part01.types.value_types.collection_types.collections_of_entities.sets.sorted_set;

public class TestBiDirectionalSortedSet {

}

//The @SortNatural and @SortComparator work the same for bidirectional sorted sets too.

// @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
// @SortNatural
// private SortedSet<Phone> phones = new TreeSet<>();
//
// @SortComparator(ReverseComparator.class)
// private SortedSet<Phone> phones = new TreeSet<>();