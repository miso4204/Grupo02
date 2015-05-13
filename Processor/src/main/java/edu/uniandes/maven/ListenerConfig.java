package edu.uniandes.maven;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class ListenerConfig {
	private static EstampateModuleConfig estampateModuleConfig=new EstampateModuleConfig();
	private static EstampateEARConfig estampateEARConfig=new EstampateEARConfig();
	private static EstampateEJBConfig estampateEJBConfig=new EstampateEJBConfig();
	public static void main(String... arg) throws IOException,
			InterruptedException {
		Path config = Paths.get("default.config");
		Path dir = Paths.get("../EstampateVabrik/configs/").toRealPath();
		System.out.println(Files.exists(dir));
		WatchService watcher = dir.getFileSystem().newWatchService();
		WatchKey key = dir.register(watcher, ENTRY_MODIFY);
		boolean procesa = true;
		for (;;) {
			key = watcher.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				if (procesa) {
					procesa = false;
					WatchEvent.Kind<?> kind = event.kind();
					WatchEvent<Path> ev = (WatchEvent<Path>) event;
					Path filename = ev.context();
					if (kind.equals(OVERFLOW)) {
						continue;
					}
					if (kind.equals(ENTRY_MODIFY) && filename.equals(config)) {
						Path featureConfig = Paths.get("../EstampateVabrik/configs/default.config").toRealPath();
						List<String> features = new ArrayList<String>();
						FileReader fr = new FileReader(featureConfig.toFile());
						BufferedReader bf = new BufferedReader(fr);
						String linea = bf.readLine();
						while (linea!= null) {
							features.add(linea);
							linea = bf.readLine();
						}
						fr.close();
						bf.close();	
						estampateModuleConfig.execute(features);
						estampateEARConfig.execute(features);
						estampateEJBConfig.execute(features);
						break;
					}
				} else {
					procesa = true;
				}

			}
			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}
}
