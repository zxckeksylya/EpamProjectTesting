package service;

import command.ParameterName;
import dbbinding.impl.AnswerBinding;
import dbbinding.impl.QuestBinding;
import entity.Answer;
import entity.Quest;
import repository.RepositoryException;
import repository.impl.SqlRepository;

import java.util.ArrayList;

//сервис проверки теста

//не доделано

public enum TestingService {
    INSTANCE;

    public int checkAnswers(ArrayList<String> test_answers, int test_id) throws RepositoryException {
        ArrayList<Quest> quests = (ArrayList<Quest>) new SqlRepository<Quest>(new QuestBinding()).getAllEntitiesWithTestId(test_id);
        ArrayList<String> answers = null;
        for (Quest quest : quests) {
            ArrayList<Answer> answers1 = (ArrayList<Answer>) new SqlRepository<Answer>(new AnswerBinding()).getAllEntitiesWithTestId(quest.getId());
            for (Answer answer : answers1) {
                answers.add(answer.getAnswerIsTrue());
            }
        }
        int k = 0;

        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals(test_answers.get(i))) { k++; }
        }

        return k;
    }

}
