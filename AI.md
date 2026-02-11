# AI Usage Documentation

## Overview
This document details the use of AI in developing the Athena chatbot application, with specific focus on GUI styling and design improvements.

## AI Tools Used
- **Claude** (Anthropic's Claude Sonnet 4.5) - Used for code generation, refactoring, and design consultation
- Gemini - Used for design consultation

## How it helped
1. **Better styling** - With the consult of AI and intentional design iterations, I was able to achieve a better UI than I would have done on my own.
2. **Faster code output, greater efficiency** - AI streamlined tedious styling and iterative code patterns which made the process significantly efficient.

## Styling and Design with AI Assistance

### 1. Greek-Themed Color Palette Design

**Task**: Design an authentic ancient Greek color scheme for the chatbot UI

**AI Contribution**:
- Researched and proposed color palette inspired by ancient Greek aesthetics:
  - Deep navy (#2c3e5a) for Athena's bubbles (representing wisdom and night sky)
  - Olive green (#577041) for user bubbles (representing Greek landscape)
  - Burnt orange/terracotta (#b8562d) for error messages (Greek pottery aesthetic)
  - Classic gold (#d4af37) for success messages and accents (divine victory)

**Code Segment** - `src/main/resources/styles/DialogBox.css`:
```css
/* User Dialog Bubble - Neutral olive green */
.user-bubble {
    -fx-background-color: #577041;
    -fx-background-radius: 20 20 4 20;
    -fx-effect: dropshadow(gaussian, rgba(26, 31, 58, 0.15), 6, 0.0, 0, 2);
    -fx-padding: 14px 18px;
}

/* Athena Dialog Bubble - Deep navy */
.athena-bubble {
    -fx-background-color: #2c3e5a;
    -fx-background-radius: 20 20 20 4;
    -fx-effect: dropshadow(gaussian, rgba(26, 31, 58, 0.18), 6, 0.0, 0, 2);
    -fx-padding: 14px 18px;
}

/* Error Dialog Bubble - Burnt orange/terracotta (Greek pottery aesthetic) */
.error-bubble {
    -fx-background-color: #b8562d;
    -fx-background-radius: 20 20 20 4;
    -fx-effect: dropshadow(gaussian, rgba(184, 86, 45, 0.3), 8, 0.0, 0, 2);
    -fx-padding: 14px 18px;
}

/* Success Dialog Bubble - Gold accent for victories */
.success-bubble {
    -fx-background-color: #d4af37;
    -fx-background-radius: 20 20 20 4;
    -fx-effect: dropshadow(gaussian, rgba(212, 175, 55, 0.4), 8, 0.0, 0, 2);
    -fx-padding: 14px 18px;
}
```

**Rationale**: AI suggested moving away from bright modern colors to earthy, historical tones that evoke ancient Greek art and architecture.

---

### 2. Modern Chat Bubble Design

**Task**: Create modern, rounded chat bubbles with asymmetric corners

**AI Contribution**:
- Designed asymmetric border radius for chat bubble "tails" (speech bubble effect)
- User bubbles: `20 20 4 20` (tail on bottom-right)
- Athena bubbles: `20 20 20 4` (tail on bottom-left)
- Calculated optimal padding (14px 18px) for text breathing room
- Applied subtle drop shadows for depth without overwhelming the UI

**Code Segment** - CSS border radius implementation:
```css
.user-bubble {
    -fx-background-radius: 20 20 4 20;  /* Top-left, Top-right, Bottom-right, Bottom-left */
}

.athena-bubble {
    -fx-background-radius: 20 20 20 4;  /* Creates tail on bottom-left */
}
```

---

### 3. Circular Avatar Implementation

**Task**: Create circular avatars with gold ring effects

**AI Contribution**:
- Implemented circular clipping using JavaFX `Circle` shape
- Calculated center point and radius for 55x55 avatars (27.5, 27.5, 27.5)
- Added gold-tinted drop shadow to create "ring" effect

**Code Segment** - `src/main/java/athena/gui/DialogBox.java`:
```java
// Make avatar circular with rounded clip
Circle clip = new Circle(27.5, 27.5, 27.5);
displayPicture.setClip(clip);
```

**Code Segment** - `src/main/resources/styles/DialogBox.css`:
```css
/* Avatar - rounded with gold ring effect */
.avatar {
    -fx-effect: dropshadow(gaussian, rgba(212, 175, 55, 0.5), 6, 0.0, 0, 2);
}
```

**Code Segment** - `src/main/resources/view/DialogBox.fxml`:
```xml
<ImageView fx:id="displayPicture"
           fitHeight="55.0"
           fitWidth="55.0"
           pickOnBounds="true"
           preserveRatio="true"
           styleClass="avatar">
```

---

### 4. Success Dialog Pattern

**Task**: Create reusable success message styling

**AI Contribution**:
- Designed gold-themed success bubble to signify "victory" in Greek theme
- Created factory method pattern matching existing error dialog approach
- Used dark text on gold background for optimal readability

**Code Segment** - `src/main/java/athena/gui/DialogBox.java`:
```java
/**
 * Creates a success dialog with success styling.
 */
public static DialogBox getSuccessDialog(String text, Image img) {
    var db = new DialogBox(text, img);
    db.flip();
    db.dialogBubble.getStyleClass().add("success-bubble");
    db.dialog.getStyleClass().add("success-label");
    return db;
}
```

---

### 5. Input Field and Send Button Styling

**Task**: Fix "Send" button text cutoff and improve input field aesthetics

**AI Contribution**:
- Diagnosed button text overflow issue
- Added minimum width constraint and increased padding
- Applied gold accent on input focus for visual feedback

**Code Segment** - `src/main/resources/styles/MainWindow.css`:
```css
/* Send Button - Gold accent */
#sendButton {
    -fx-background-color: #d4af37;
    -fx-text-fill: #1a1f3a;
    -fx-font-family: "Cinzel";
    -fx-font-size: 14px;
    -fx-font-weight: bold;
    -fx-background-radius: 25;
    -fx-cursor: hand;
    -fx-padding: 12 28 12 28;
    -fx-min-width: 95px;  /* Prevents text cutoff */
    -fx-effect: dropshadow(gaussian, rgba(212, 175, 55, 0.3), 4, 0.0, 0, 2);
}

#userInput:focused {
    -fx-border-color: #d4af37;  /* Gold accent on focus */
}
```

---

## Iterative Design Process

The styling went through several AI-guided iterations:

1. **Initial**: Plain blue bubbles with bright red errors
2. **Iteration 1**: Added gold borders to Athena bubbles
3. **Iteration 2**: Removed borders (user feedback: too busy)
4. **Final**: Clean bubbles with distinct background colors, success state added

This demonstrates effective human-AI collaboration where the AI proposes solutions and adapts to user feedback.

