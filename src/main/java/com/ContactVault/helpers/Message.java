package com.ContactVault.helpers;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String content;

    @Builder.Default
    private MessageType type=MessageType.blue;
}
