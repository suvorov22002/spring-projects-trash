/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.dependency.tools;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * {@link ManagedDependencies} implementation backed a maven POM.
 * 
 * @author Phillip Webb
 * @since 1.1.0
 */
public class PomManagedDependencies extends AbstractManagedDependencies {

	/**
	 * Create a new {@link PomManagedDependencies} instance.
	 * @param effectivePomInputStream the effective POM containing resolved versions. The
	 * input stream will be closed once content has been loaded.
	 * @param dependenciesInputStream and optional POM used to limit the dependencies. The
	 * input stream will be closed once content has been loaded. which will be added from
	 * the effective POM
	 */
	public PomManagedDependencies(InputStream effectivePomInputStream,
			InputStream dependenciesInputStream) {
		try {
			Document effectivePom = readDocument(effectivePomInputStream);
			Document dependenciesPom = readDocument(dependenciesInputStream);

			if (dependenciesPom == null) {
				// No dependencies POM, add all items
				for (Dependency dependency : readDependencies(effectivePom)) {
					add(new ArtifactAndGroupId(dependency), dependency);
				}
			}
			else {
				// Only add items that are also in the dependencies POM
				Map<ArtifactAndGroupId, Dependency> all = new HashMap<ArtifactAndGroupId, Dependency>();
				for (Dependency dependency : readDependencies(effectivePom)) {
					all.put(new ArtifactAndGroupId(dependency), dependency);
				}
				for (Dependency dependency : readDependencies(dependenciesPom)) {
					ArtifactAndGroupId artifactAndGroupId = new ArtifactAndGroupId(
							dependency);
					Dependency effectiveDependency = all.get(artifactAndGroupId);
					if (effectiveDependency != null) {
						add(artifactAndGroupId, effectiveDependency);
					}
				}
			}
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
	}

	private Document readDocument(InputStream inputStream) throws Exception {
		if (inputStream == null) {
			return null;
		}
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			document.getDocumentElement().normalize();
			return document;
		}
		finally {
			inputStream.close();
		}
	}

	private List<Dependency> readDependencies(Document document) throws Exception {
		Element element = (Element) document.getElementsByTagName("project").item(0);
		element = (Element) element.getElementsByTagName("dependencyManagement").item(0);
		element = (Element) element.getElementsByTagName("dependencies").item(0);
		NodeList nodes = element.getChildNodes();
		List<Dependency> dependencies = new ArrayList<Dependency>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node instanceof Element) {
				dependencies.add(Dependency.fromDependenciesXml((Element) node));
			}
		}
		return dependencies;
	}

}
