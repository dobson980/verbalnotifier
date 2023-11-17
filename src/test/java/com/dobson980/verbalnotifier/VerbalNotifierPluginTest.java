package com.dobson980.verbalnotifier;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VerbalNotifierPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VerbalNotifierPlugin.class);
		RuneLite.main(args);
	}
}