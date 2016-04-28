CREATE TABLE Building (
    b_id VARCHAR2(20) NOT NULL,
    b_name VARCHAR2(100),
    b_vertices NUMBER,
    b_shape SDO_GEOMETRY,
    PRIMARY KEY (b_id)
);

CREATE TABLE Fire_Hydrant (
    h_id VARCHAR2(20) NOT NULL,
    h_location SDO_GEOMETRY,     
    PRIMARY KEY (h_id)
); 

CREATE TABLE Fire_Building (
    fb_id VARCHAR2(20) NOT NULL,        
    PRIMARY KEY (fb_id),
    FOREIGN KEY(fb_id) REFERENCES Building(b_id)
);
COMMIT;

INSERT INTO user_sdo_geom_metadata(TABLE_NAME,COLUMN_NAME,DIMINFO,SRID) VALUES (
    'Building',
         'b_shape',
         SDO_DIM_ARRAY(
         SDO_DIM_ELEMENT('X', 0, 800, 0.005),
      SDO_DIM_ELEMENT('Y', 0, 600, 0.005)),
       NULL);

INSERT INTO user_sdo_geom_metadata(TABLE_NAME,COLUMN_NAME,DIMINFO,SRID) 
      VALUES (
       'Fire_Hydrant',
         'h_location',
         SDO_DIM_ARRAY(
         SDO_DIM_ELEMENT('X', 0, 800, 0.005),
      SDO_DIM_ELEMENT('Y', 0, 600, 0.005)),
       NULL);
COMMIT;

CREATE INDEX Building_indx ON Building(b_shape) INDEXTYPE IS MDSYS.SPATIAL_INDEX;
CREATE UNIQUE INDEX Building_unique_indx ON Building (b_name);
CREATE INDEX Fire_Hydrant_indx ON Fire_Hydrant(h_location) INDEXTYPE IS MDSYS.SPATIAL_INDEX;
COMMIT;
