package com.porkin.listener;

import com.porkin.compositekeys.ExpenseSplitIDs;
import com.porkin.entity.FriendshipEntity;
import com.porkin.entity.ExpenseEntity;
import com.porkin.entity.ExpenseSplitEntity;
import com.porkin.repository.FriendshipRepository;
import com.porkin.repository.ExpenseSplitRepository;
import jakarta.persistence.PostPersist;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpenseSplitListener {

  @Autowired
  private ExpenseSplitRepository expenseSplitRepository;

  @Autowired
  private FriendshipRepository friendshipRepository;

  @PostPersist
  public void onExpenseCreated(ExpenseEntity expenseEntity) {
    Long idExpenseCreator = expenseEntity.getExpenseCreator().getId();
    List<FriendshipEntity> friendships = friendshipRepository.findByIdUser(idExpenseCreator);

    friendships.forEach(friendship -> {

      ExpenseSplitIDs idsSplit = new ExpenseSplitIDs();
      idsSplit.setIdExpense(expenseEntity.getId());
      idsSplit.setIdFriend(expenseEntity.getId());

      ExpenseSplitEntity split = new ExpenseSplitEntity();
      split.setExpenseSplitIDs(idsSplit);
      split.setIdExpenseCreator(expenseEntity.getExpenseCreator());
      split.setPaid(false);

      expenseSplitRepository.save(split);

    });
  }
}
