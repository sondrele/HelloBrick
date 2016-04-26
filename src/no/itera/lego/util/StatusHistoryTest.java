package no.itera.lego.util;

import no.itera.lego.websocket.WebSocketListener;
import no.itera.lego.color.Color;
import no.itera.lego.message.Status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatusHistoryTest {

    @Mock
    WebSocketListener listener;

    @Test
    public void shouldCallListenerButNotWaitForIt() throws InterruptedException {
        StatusHistory statusHistory = new StatusHistory();

        statusHistory.addListener(listener);
        statusHistory.addNewStatus(new Status(
                true,
                false,
                Color.UNDEFINED,
                Collections.<Color>emptyList()));

        Thread.sleep(10);

        verify(listener, times(1)).initialStatusFired(any(Status.class));
    }

    @Test
    public void shouldCallListenerWithTwoLatestEvents() throws InterruptedException {
        StatusHistory statusHistory = new StatusHistory();

        statusHistory.addListener(listener);
        statusHistory.addNewStatus(new Status(
                true,
                false,
                Color.UNDEFINED,
                Collections.<Color>emptyList()));
        Thread.sleep(10);
        statusHistory.addNewStatus(new Status(
                true,
                false,
                Color.RED,
                Collections.<Color>emptyList()));
        Thread.sleep(10);
        Status lastStatus = new Status(
                true,
                false,
                Color.BLACK,
                Collections.<Color>emptyList());
        statusHistory.addNewStatus(lastStatus);
        Thread.sleep(10);

        verify(listener, times(1)).initialStatusFired(any(Status.class));
        verify(listener, times(2)).newStatusFired(any(Status.class), any(Status.class));
    }

}