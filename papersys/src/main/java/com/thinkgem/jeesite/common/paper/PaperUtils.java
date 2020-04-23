package com.thinkgem.jeesite.common.paper;

public class PaperUtils {
	public static double paperMatch(String content1, String content2) {
		double score = CosineSimilarity.getSimilarity(content1, content2);
		return score;
	}
}
