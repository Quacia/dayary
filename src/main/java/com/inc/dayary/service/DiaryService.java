package com.inc.dayary.service;

import java.util.List;

import javax.validation.Valid;

import com.inc.dayary.domain.Diary;

public interface DiaryService {
	public List<Diary> list();

	public void add(Diary diary);

	public List<Diary> list(String id);
	
}
