/*
 * Copyright (c) 2012, 2014, Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.mdpnp.util.rotator;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.oracle.javafx.scenebuilder.kit.util.control.paintpicker.gradientpicker.GradientPicker;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class RotatorControl extends GridPane {

	private static final Logger log = LoggerFactory.getLogger(RotatorControl.class);

	@FXML
	private Button rotator_dial;
	@FXML
	private Button rotator_handle;

	private final int roundingFactor = 100; // 2 decimals rounding

	private final DoubleProperty rotation = new SimpleDoubleProperty();

	public RotatorControl(String text) {
		initialize(text);
	}

	public final DoubleProperty rotationProperty() {
		return rotation;
	}

	public final double getRotationProperty() {
		return rotation.get();
	}

	public final void setRotationProperty(double value) {
		rotation.set(value);
	}

	private void initialize(String text) {
		log.info("RotatorControl Connected...");

		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(RotatorControl.class.getResource("RotatorControl.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			// Logger.getLogger(GradientPicker.class.getName()).log(Level.SEVERE, null, ex);
		}

		rotator_dial.setOnAction((ActionEvent event) -> {
			event.consume();
		});
		rotator_handle.setOnAction((ActionEvent event) -> {
			event.consume();
		});
	}

	@FXML
	void rotatorAction(ActionEvent event) {
		event.consume();
	}

	@FXML
	void rotatorMousePressed(MouseEvent e) {
		rotatorMouseDragged(e);
	}

	@FXML
	void rotatorMouseDragged(MouseEvent e) {
		final Parent p = rotator_dial.getParent();
		final Bounds b = rotator_dial.getLayoutBounds();
		final Double centerX = b.getMinX() + (b.getWidth() / 2);
		final Double centerY = b.getMinY() + (b.getHeight() / 2);
		final Point2D center = p.localToParent(centerX, centerY);
		final Point2D mouse = p.localToParent(e.getX(), e.getY());
		final Double deltaX = mouse.getX() - center.getX();
		final Double deltaY = mouse.getY() - center.getY();
		final Double radians = Math.atan2(deltaY, deltaX);

		rotate(Math.toDegrees(radians));
	}

	public static double rate;

	private void rotate(Double value) {
		double rounded = round(value, roundingFactor);

		if (rounded >= 130.00 && rounded <= 180.00) {
			rotation.set(rounded);
			rotator_handle.setRotate(rounded);
			rounded = map(rounded, 130, 180, 0, 50);
		} else if (rounded >= -179.99 && rounded <= 0.00) {
			rotation.set(rounded);
			rotator_handle.setRotate(rounded);
			rounded = map(rounded, -179.99, 0, 50.01, 230);
		} else if (rounded >= 0.01 && rounded <= 50) {
			rotation.set(rounded);
			rotator_handle.setRotate(rounded);
			rounded = map(rounded, 0.01, 50.00, 230.01, 280.00);
		}
		rate = map(rounded, 0, 280, 0, 10);
	}

	private double round(double value, int roundingFactor) {
		double doubleRounded = Math.round(value * roundingFactor);
		return doubleRounded / roundingFactor;
	}

	private static double map(double input, double old_min_range, double old_max_range, double new_min_range,
			double new_max_range) {
		double math = (input - old_min_range) * (new_max_range - new_min_range) / (old_max_range - old_min_range)
				+ new_min_range;
		return math;
	}
}
