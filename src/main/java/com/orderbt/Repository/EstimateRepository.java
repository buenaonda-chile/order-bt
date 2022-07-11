package com.orderbt.Repository;

import com.orderbt.Domain.*;
import com.orderbt.Dto.EstimateDto;
import com.orderbt.Dto.ItemDto;
import com.orderbt.Dto.OrderDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.orderbt.Domain.QItem.item;
import static com.orderbt.Domain.QEstimate.estimate;

@Repository
@RequiredArgsConstructor
public class EstimateRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager em;

    public Estimate findById(Long id){
        return em.find(Estimate.class, id);
    }

    public Estimate findEstimate(EstimateDto dto){
        return jpaQueryFactory
                .selectFrom(estimate)
                .where(estimate.id.eq(dto.getId()))
                .where(estimate.name.eq(dto.getName()))
                .fetchFirst();
    }

    public List<Item> getCellModel(ItemDto dto){
      return jpaQueryFactory
              .selectFrom(item)
              .where(item.category.eq(dto.getCategory()))
              .fetch();
    }

    public List<Item> getPcm(ItemDto dto){
        return jpaQueryFactory
                .selectFrom(item)
                .where(item.category.eq("PCM"))
                .where(item.name.contains(dto.getCategory()))
                .where(item.name.contains(dto.getDetail()))
                .where(item.active.eq("Y"))
                .fetch();
    }

    public List<Item> getPcmAll(ItemDto dto){
        return jpaQueryFactory
                .selectFrom(item)
                .where(item.name.contains(dto.getCategory()))
                .where(item.category.eq("PCM"))
                .where(item.active.eq("Y"))
                .fetch();
    }

    public List<Item> getCase(){
        return jpaQueryFactory
                .selectFrom(item)
                .where(item.category.eq("케이스"))
                .where(item.active.eq("Y"))
                .fetch();
    }

    public Order findOrderById(Long id) { return em.find(Order.class, id); }

    public void createEstimate(Estimate estimate){
        em.persist(estimate);
    }

    public void createOrder(Order order){
        em.persist(order);
    }

    public void createReservation(Reservation reservation){em.persist(reservation);}
}
