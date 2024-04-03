package com.kiwipedia.model;

import java.time.LocalDate;


public record User(int id, String nick, String email, LocalDate dataZalozenia) {
}
