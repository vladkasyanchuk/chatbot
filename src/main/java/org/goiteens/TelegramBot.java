package org.goiteens;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {
    public TelegramBot() {
        ChatBot.initDreams();
        ChatBot.initProfessions();
    }
    
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            String response = ChatBot.process(message);
          
            sendText(update.getMessage().getChatId(), response);
        }
    }
    
    private void sendText(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "vladkas_bot";
    }

    @Override
    public String getBotToken() {
        return "1490216074:AAGhE84jpQ7HU5UoafHYU13QaoIV0LO13b4";
    }
}
