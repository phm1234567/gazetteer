/**
 * Read, parse, filter, build geometry and slice it
 *
 * Overview
 * --------
 * 
 * 1. Read Relations, Ways and Nodes.
 * 2. Filter them by tags
 * 3. Build geometry (find nodes for relations and ways)
 * 4. Slice geometry (boundaries and ways) into stripes. 
 *    So join phase may be performed in a parallel way.
 *    
 * Details
 * -------
 * 
 * * Entry point of split process is me.osm.gazetter.striper.Slicer.run
 * 
 * * me.osm.gazetter.striper.Slicer runs reading and filtering, and for
 *   red and filtered objects slices geometry into smaller peaces.
 * 
 * * me.osm.gazetter.striper.readers are responsible for xml stream parsing,
 *   they reads xml, create POJOs and calls 
 *   me.osm.gazetter.striper.readers.PointsReader.PointsHandler, 
 *   me.osm.gazetter.striper.readers.WaysReader.WaysHandler 
 *   and me.osm.gazetter.striper.readers.RelationsReader.RelationsHandler accordingly.
 * 
 * 
 * * me.osm.gazetter.striper.builders are responsible for filtering xml data
 *   parsed by me.osm.gazetter.striper.readers and building features like 
 *   boundaries highways etc. and their geometry.
 * 
 * * After me.osm.gazetter.striper.builders have built a feature it calls 
 *   their me.osm.gazetter.striper.builders.handlers and passes builded feature.
 * 
 * */
package me.osm.gazetter.striper;