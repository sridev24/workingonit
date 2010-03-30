/*
 * Copyright 2008-2009 Vladimir Ritz Bossicard
 *
 * This file is part of WorkingOnIt.
 *
 * WorkingOnIt is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * Version     : $Revision: 288 $
 * Last edit   : $Date: 2009-11-28 20:32:49 +0100 (Sat, 28 Nov 2009) $
 * Last editor : $Author: vbossica $
 */
package org.workingonit.clusterus.singleton;

/**
 * Classes implementing this interface are considered as <i>business
 * singletons</i> and the {@link SingletonManager} insures that only one of such
 * service is running in a {@link Cluster} at any given time.
 * 
 * @author Vladimir Ritz Bossicard
 */
public interface SingletonService {

    void start() throws Exception;

    void stop() throws Exception;

}
