package com.demoaut.newtours.runners;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import com.demoaut.newtours.utils.DataToFeature;
import com.demoaut.newtours.utils.Functions;

import net.serenitybdd.cucumber.CucumberWithSerenity;

public class RunnerPersonalizado extends Runner {
	private Class<CucumberWithSerenity> classValue;
	private CucumberWithSerenity cucumberWithSerenity;

	public RunnerPersonalizado(Class<CucumberWithSerenity> classValue) throws Exception {
		this.classValue = classValue;
		cucumberWithSerenity = new CucumberWithSerenity(classValue);
	}

	@Override
	public Description getDescription() {
		return cucumberWithSerenity.getDescription();
	}

	@Override
	public void run(RunNotifier notifier) {
		try {
			Functions.txtResultado("FAILED");
			DataToFeature.backUpFeaturesFile();
			DataToFeature.overrideFeatureFiles();
			cucumberWithSerenity = new CucumberWithSerenity(classValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cucumberWithSerenity.run(notifier);
		DataToFeature.restoreBackUpFeatures();
	}
}
