package io.github.sudharsan_selvaraj.audiologger;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class LogOptions extends SpeechOptions {

   private List<String> excludedMethods = Collections.emptyList();

   private boolean logBeforeCommand = false;

   private boolean logAfterCommand = false;

   private boolean logException = true;

}
