/*******************************************************************************
 * Copyright (c) 2015, 2015 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.ide.core.operations;

import java.nio.file.Path;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

import melnorme.lang.ide.core.LangCore;
import melnorme.utilbox.concurrency.OperationCancellation;
import melnorme.utilbox.core.CommonException;
import melnorme.utilbox.process.ExternalProcessHelper.ExternalProcessResult;

public abstract class AbstractToolManagerOperation implements IBuildTargetOperation {
	
	protected final IProject project;
	
	public AbstractToolManagerOperation(IProject project) {
		this.project = project;
	}
	
	public IProject getProject() {
		return project;
	}
	
	protected AbstractToolManager getToolManager() {
		return LangCore.getToolManager();
	}
	
	protected Path getBuildToolPath() throws CommonException {
		return getToolManager().getSDKToolPath();
	}
	
	protected ExternalProcessResult runBuildTool(IProgressMonitor monitor, ProcessBuilder pb) 
			throws CommonException, OperationCancellation {
		return getToolManager().newRunToolTask(pb, getProject(), monitor).runProcess();
	}
	
}