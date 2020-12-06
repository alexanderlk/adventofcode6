package no.alexander.AdventOfCode;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventOfCode5Application implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(AdventOfCode5Application.class);

	private static final String BYR = "byr";
	private static final String IYR = "iyr";
	private static final String EYR = "eyr";
	private static final String HGT = "hgt";
	private static final String HCL = "hcl";
	private static final String ECL = "ecl";
	private static final String PID = "pid";
	private static final String CID = "cid";
	
	public static void main(String[] args) {
		SpringApplication.run(AdventOfCode5Application.class, args);
	}

	@Override
	public void run(String... args) throws URISyntaxException, IOException {
		URL input = getClass().getClassLoader().getResource("input.txt");
		File file = new File(input.toURI());
		
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
		
		List<Group> groups = new ArrayList<>();
		Group group = new Group();
		groups.add(group);
		
		for ( String line : lines ) {
			if (line.isBlank()) {
				group = new Group();
				groups.add(group);
			} else {
				group.parseLine(line);
			}
		}
		
		
		partOne(groups);
		partTwo(groups);
	}
	
	private void partOne(List<Group> groups) {
		var answers = 0;
		for ( Group g : groups ) {
			answers += g.getCountPartOne();
		}
		LOG.info("Part one - " + answers);
	}
	
	
	
	private void partTwo(List<Group> groups) {
		var answers = 0;
		for ( Group g : groups ) {
			answers += g.getCountPartTwo();
		}
		LOG.info("Part one - " + answers);
	}
	
	private class Group {
		private List<Character> answers = new ArrayList<>();
		
		private List<List<Character>> persons = new ArrayList<>();

		public void parseLine(String line) {
			partOneParse(line);
			partTwoParse(line);
		}
		
		private void partOneParse(String line) {
			for ( char c : line.toCharArray() ) {
				if (!answers.contains(c)) {
					answers.add(c);
				}
			}
		}
		
		private void partTwoParse(String line) {
			List<Character> answers = new ArrayList<>();
			for (char c : line.toCharArray()) {
				answers.add(c);
			}
			persons.add(answers);
		}
		
		public int getCountPartOne() {
			return answers.size();
		}
		
		public int getCountPartTwo() {
			var count = 0;
			List<Character> chars = persons.get(0);
			for (Character c : chars) {
				var existsInAll = true;
				for (List<Character> cc : persons) {
					if (!cc.contains(c)) {
						existsInAll = false;
					}
				}
				if(existsInAll) {
					count++;
				}
			}
			
			return count;
		}
	}
	
	
	
}
